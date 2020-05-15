import { Component } from '@angular/core';
import { UploadFile } from 'ng-zorro-antd/upload';

function getBase64(file: File): Promise<string | ArrayBuffer | null> {
  return new Promise((resolve, reject) => {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => resolve(reader.result);
    reader.onerror = error => reject(error);
  });
}

@Component({
  selector: 'app-upload-picture',
  template: `
    <div class="clearfix">
      <nz-upload
        nzAction="./"
        nzListType="picture-card"
        [(nzFileList)]="fileList"
        [nzShowButton]="fileList.length < 4"
        [nzPreview]="handlePreview"
        [nzFileType]="'image/png,image/jpeg,image/gif,image/bmp'"
      >
        <i nz-icon nzType="plus"></i>
        <div class="ant-upload-text">Upload</div>
      </nz-upload>
      <nz-modal [nzVisible]="previewVisible" [nzContent]="modalContent" [nzFooter]="null" (nzOnCancel)="previewVisible = false">
        <ng-template #modalContent>
          <img [src]="previewImage" [ngStyle]="{ width: '100%' }" />
        </ng-template>
      </nz-modal>
    </div>
  `,
  styles: [
    `
      i[nz-icon] {
        font-size: 32px;
        color: #999;
      }
      .ant-upload-text {
        margin-top: 8px;
        color: #666;
      }
    `
  ]
})
export class UploadPictureComponent {
  fileList = [
    {
      uid: '-1',
      name: 'image.png',
      status: 'done',
      url: '../../../assets/pictures/samsung1.jpg'
    },
    {
      uid: '-2',
      name: 'image.png',
      status: 'done',
      url: '../../../assets/pictures/samsung2.jpg'
    },

  ];
  previewImage: string | undefined = '';
  previewVisible = false;

  handlePreview = async (file: UploadFile) => {
    if (!file.url && !file.preview) {
      file.preview = await getBase64(file.originFileObj!);
    }
    this.previewImage = file.url || file.preview;
    this.previewVisible = true;
  };
}
