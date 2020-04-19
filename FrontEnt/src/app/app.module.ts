import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';

import { registerLocaleData } from '@angular/common';

import { IconDefinition } from '@ant-design/icons-angular';
import * as AllIcons from '@ant-design/icons-angular/icons';

//NzModule
// import { NzFormModule } from 'ng-zorro-antd/form';
// import { NzI18nModule } from 'ng-zorro-antd/i18n';
// import { NzIconModule } from 'ng-zorro-antd/icon';
// import { NzInputModule } from 'ng-zorro-antd/input';
// import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
// import { NzButtonModule } from 'ng-zorro-antd/button';
// import { NzCheckboxModule } from 'ng-zorro-antd/checkbox';
// import { NzDropDownModule } from 'ng-zorro-antd/dropdown';
// import { NzSpaceModule } from 'ng-zorro-antd/space';
// import { NzSelectModule } from 'ng-zorro-antd/select';
// import { NzMenuModule } from 'ng-zorro-antd/menu';
// import { NzGridModule } from 'ng-zorro-antd/grid';

import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';


//MyCompents
import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './item-specifications/item-specifications.component';

const antDesignIcons = AllIcons as {
   [key: string]: IconDefinition;
 };
 const icons: IconDefinition[] = Object.keys(antDesignIcons).map(key => antDesignIcons[key])

@NgModule({
   declarations: [
      AppComponent,
      LoginComponent,
      SignupSellerComponent,
      SignupBuyerComponent,
      ItemSearchComponent,
      TopBarComponent,
      BuyerMainComponent,
      ItemSpecificationsComponent
   ],
   imports: [
      BrowserModule,
      NgbModule,
      FormsModule,
      DragDropModule,
      ScrollingModule,
      RouterModule.forRoot([
         { path: '', component: ItemSpecificationsComponent },
       ]),
       ReactiveFormsModule,
       BrowserAnimationsModule,
      DemoNgZorroAntdModule,
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { 
}
