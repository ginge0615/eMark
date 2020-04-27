import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

//NzModule
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzDividerModule } from 'ng-zorro-antd/divider';
import { NzDrawerModule } from 'ng-zorro-antd/drawer';
import { NzI18nModule } from 'ng-zorro-antd/i18n';
import { NzIconModule } from 'ng-zorro-antd/icon';
import { NzInputModule } from 'ng-zorro-antd/input';
import { NzInputNumberModule } from 'ng-zorro-antd/input-number';
import { NzLayoutModule } from 'ng-zorro-antd/layout';
import { NzSelectModule } from 'ng-zorro-antd/select';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzTableModule } from 'ng-zorro-antd/table';
import { NzGridModule } from 'ng-zorro-antd/grid';
import { NzSpaceModule } from 'ng-zorro-antd/space';
import { NzCarouselModule } from 'ng-zorro-antd/carousel';
import { NzTabsModule } from 'ng-zorro-antd/tabs';
import { NzUploadModule } from 'ng-zorro-antd/upload';
import { NzModalModule } from 'ng-zorro-antd/modal';
import { NzDatePickerModule } from 'ng-zorro-antd/date-picker';
import { NzBadgeModule } from 'ng-zorro-antd/badge';
import { NzRadioModule } from 'ng-zorro-antd/radio';


//MyCompents
import { LoginComponent } from './login/login.component';
import { SignupSellerComponent } from './seller/signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './buyer/signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './buyer/item-search/item-search.component';
import { TopBarComponent } from './common/top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer/buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './buyer/item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './buyer/shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './buyer/purchase-history/purchase-history.component';
import { SellerMainComponent } from './seller/seller-main/seller-main.component';
import { AddItemComponent } from './seller/add-item/add-item.component';
import { UploadPictureComponent } from './seller/add-item/upload-picture/upload-picture.component';
import { ManageStockComponent } from './seller/manage-stock/manage-stock.component';
import {ReportsComponent} from './seller/reports/reports.component';

@NgModule({
   declarations: [
      AppComponent,
      LoginComponent,
      SignupSellerComponent,
      SignupBuyerComponent,
      ItemSearchComponent,
      TopBarComponent,
      BuyerMainComponent,
      ItemSpecificationsComponent,
      ShoppingCartComponent,
      PurchaseHistoryComponent,
      SellerMainComponent,
      AddItemComponent,
      UploadPictureComponent,
      ManageStockComponent,
      ReportsComponent
   ],
   imports: [
      AppRoutingModule,
      BrowserModule,
      NgbModule,
      FormsModule,
      DragDropModule,
      ScrollingModule,
      ReactiveFormsModule,
      BrowserAnimationsModule,
      NzButtonModule,
      NzDividerModule,
      NzDrawerModule,
      NzI18nModule,
      NzIconModule,
      NzInputModule,
      NzInputNumberModule,
      NzLayoutModule,
      NzSelectModule,
      NzFormModule,
      NzTableModule,
      NzGridModule,
      NzSpaceModule,
      NzCarouselModule,
      NzTabsModule,
      NzUploadModule,
      NzModalModule,
      NzDatePickerModule,
      NzBadgeModule,
      NzRadioModule,
      HttpClientJsonpModule, 
      HttpClientModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule {
}
