import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { ScrollingModule } from '@angular/cdk/scrolling';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HTTP_INTERCEPTORS, HttpClientJsonpModule, HttpClientModule } from '@angular/common/http';

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
import { NzDescriptionsModule } from 'ng-zorro-antd/descriptions';
import { NzAlertModule } from 'ng-zorro-antd/alert';


//MyCompents
import { LoginComponent } from './component/common/login/login.component';
import { SignupSellerComponent } from './component/seller/signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './component/buyer/signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './component/buyer/item-search/item-search.component';
import { TopBarComponent } from './component/common/top-bar/top-bar.component';
import { BuyerMainComponent } from './component/buyer/buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './component/buyer/item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './component/buyer/shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './component/buyer/purchase-history/purchase-history.component';
import { SellerMainComponent } from './component/seller/seller-main/seller-main.component';
import { AddItemComponent } from './component/seller/add-item/add-item.component';
import { UploadPictureComponent } from './component/seller/add-item/upload-picture/upload-picture.component';
import { ManageStockComponent } from './component/seller/manage-stock/manage-stock.component';
import {ReportsComponent} from './component/seller/reports/reports.component';

import {JwtInterceptor} from './interceptor/jwt.interceptor';
import { UserService } from './services/user.service'

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
      NzDescriptionsModule,
      NzAlertModule,
      HttpClientJsonpModule,
      HttpClientModule
   ],
   providers: [
      { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
      UserService
   ],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule {
}
