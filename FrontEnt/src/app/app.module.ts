import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { RouterModule } from '@angular/router';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/zh-Hans';
import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer-main/buyer-main.component';

import { NzButtonModule } from 'ng-zorro-antd/button';


@NgModule({
   declarations: [
      AppComponent,
      LoginComponent,
      SignupSellerComponent,
      SignupBuyerComponent,
      ItemSearchComponent,
      TopBarComponent,
      BuyerMainComponent
   ],
   imports: [
      BrowserModule,
      NgbModule,
      RouterModule.forRoot([
         { path: '', component: BuyerMainComponent },
       ]),
      NzButtonModule
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { 
}
