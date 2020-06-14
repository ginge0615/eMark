import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Option } from 'src/app/models/option';
import { UploadFile } from 'ng-zorro-antd/upload';
import { OptionsService } from 'src/app/services/options.service';
import { MessageService } from 'src/app/services/message.service';
import { NzMessageService } from 'ng-zorro-antd/message';
import { ItemService } from 'src/app/services/item.service';
import { GlobalService } from 'src/app/services/global.service';
import { Router } from '@angular/router';
import { Observable, Observer } from 'rxjs';
import { environment } from 'src/environments/environment';

function getBase64(file: File): Promise<string | ArrayBuffer | null> {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

@Component({
  selector: 'app-add-item',
  templateUrl: './add-item.component.html',
  styleUrls: ['./add-item.component.css']
})
export class AddItemComponent implements OnInit {

  labelSize = 7;
  controlSize = 14;

  validateForm: FormGroup;

  //item
  categoryOptionList: Option[] = [];
  selectedCategory: Option;
  subCategoryOptionList: Option[] = [];
  selectedSubCategory: Option;
  manufacturerOptionList: Option[] = [];
  selectedManufacturer: Option;
  itemNameValue: string;
  priceValue: number = 1;
  stockValue: number = 1;

  //picture
  pictureControls:UploadFile[] = [];
  previewImage: string | undefined = '';
  previewVisible = false;

  //description
  descriptionControls: Array<{ id: number; controlInstance: string; value:string }> = [];
  descriptionValues : string[];

  compareFn = (o1: any, o2: any) => (o1 && o2 ? o1.value === o2.value : o1 === o2);
  formatterDollar = (value: number) => `$ ${value}`;
  parserDollar = (value: string) => value.replace('$ ', '');
  formatterPercent = (value: number) => `${value} %`;
  parserPercent = (value: string) => value.replace(' %', '');

  uploadUrl : string = environment.baseUrl + "/picture";

  constructor(private fb: FormBuilder,
    private optionsService: OptionsService,
    private msgService: MessageService,
    private msgPopup: NzMessageService,
    private itemService : ItemService,
    private globalService : GlobalService,
    private router: Router) {
    this.msgService.hideMessage();
  }

  ngOnInit() {
    this.validateForm = this.fb.group({
      category: new FormControl(null, [Validators.required]),
      subCategory: new FormControl(null, [Validators.required]),
      manufacturer: new FormControl(null, [Validators.required]),
      itemName: new FormControl(null, [Validators.required]),
      price: new FormControl(null, [Validators.required]),
      stock: new FormControl(null, [Validators.required]),
    });

    this.initOptions();

    this.addField();
  }

  private initOptions() {
    //Init Category Options
    this.optionsService.getCategoryOptions().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.categoryOptionList = respData;
      },
      res => {
        //error
        const response: any = res;
      }
    );

    //Init Manufactur Options
    this.optionsService.getManufacturOptions().subscribe(
      data => {
        //successful
        const respData: any = data;
        this.manufacturerOptionList = respData;
      },
      res => {
        //error
        const response: any = res;
      }
    );
  }

  changeCategory(selectedOption: Option): void {
    if (selectedOption) {
      this.optionsService.getSubCategoryOptions(selectedOption.value).subscribe(
        data => {
          //successful
          const respData: any = data;
          this.subCategoryOptionList = respData;
        },
        res => {
          //error
          const response: any = res;
        }
      );
    }
  }

  beforeUpload = (file: UploadFile, _fileList: UploadFile[]) => {
    return new Observable((observer: Observer<boolean>) => {
      const isJpgOrPng = file.type === 'image/jpeg' || file.type === 'image/png' || file.type === 'image/jif' || file.type === 'image/jpg';
      if (!isJpgOrPng) {
        this.msgPopup.error('You can only upload JPG file!');
        observer.complete();
        return;
      }
      const isLt2M = file.size! / 1024 / 1024 < 2;
      if (!isLt2M) {
        this.msgPopup.error('Image must smaller than 2MB!');
        observer.complete();
        return;
      }
      observer.next(isJpgOrPng && isLt2M);
      observer.complete();
    });
  };


  submitForm(): void {
    let hasError: boolean = false;

    for (const i in this.validateForm.controls) {
      this.validateForm.controls[i].markAsDirty();
      this.validateForm.controls[i].updateValueAndValidity();

      if (this.validateForm.controls[i].errors) {
        hasError = true;
      }
    }
    
    if (this.pictureControls.length == 0) {
      this.msgPopup.error("Please upload at least one picture.");
      hasError = true;
    }

    if (hasError) return;


    this.msgService.hideMessage();

    let pictures : string[] = [];
    for (let picture of this.pictureControls) {
      pictures.push(picture.response.path);
    }

    let descriptions : string[] = [];
    for (let description of this.descriptionControls) {
      descriptions.push(description.value);
    }

    let model = {
      category:this.selectedCategory.value,
      subcategory:this.selectedCategory.value,
      manufactur:this.selectedManufacturer.value,
      itemName:this.itemNameValue,
      price:this.priceValue,
      seller: this.globalService.getUserId(),
      stock:this.stockValue,
      pictures:pictures,
      descriptions:descriptions
    }

    this.itemService.addItem(model).subscribe(
      data => {
        //successful
        const respData: any = data;
        this.router.navigate(['/additem-success']);
      },
      res => {
        //error
        const response: any = res;
        this.router.navigate(['/server-error',response.status]);
      }
    );


  }

  addField(e?: MouseEvent): void {
    if (e) {
      e.preventDefault();
    }
    const id = this.descriptionControls.length > 0 ? this.descriptionControls[this.descriptionControls.length - 1].id + 1 : 0;

    const control = {
      id,
      controlInstance: `description${id}`,
      value:""
    };
    const index = this.descriptionControls.push(control);
    this.validateForm.addControl(this.descriptionControls[index - 1].controlInstance, new FormControl(null, Validators.required));
  }

  removeField(i: { id: number; controlInstance: string; value:string }, e: MouseEvent): void {
    e.preventDefault();
    if (this.descriptionControls.length > 1) {
      const index = this.descriptionControls.indexOf(i);
      this.descriptionControls.splice(index, 1);
      this.validateForm.removeControl(i.controlInstance);
    }
  }

  handlePreview = async (file: UploadFile) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj!);
    }
    this.previewImage = file.url || file.preview;
    this.previewVisible = true;
  };

  handleUploadPictureChange(info: { file: UploadFile }): void {
    switch (info.file.status) {
      case 'uploading':
        break;
      case 'done':
        info.file.url = info.file.response.path;
        break;
      case 'error':
        this.msgPopup.error('Server error');
        break;
    }
  }
}
