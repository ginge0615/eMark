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
import { DemoNgZorroAntdModule } from './ng-zorro-antd.module';


//MyCompents
import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';

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
      ItemSpecificationsComponent,
      ShoppingCartComponent,
      PurchaseHistoryComponent
   ],
   imports: [
      BrowserModule,
      NgbModule,
      FormsModule,
      DragDropModule,
      ScrollingModule,
      ReactiveFormsModule,
      BrowserAnimationsModule,
      DemoNgZorroAntdModule,
      RouterModule.forRoot([
         { path: '', component: ShoppingCartComponent },
       ])
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule { 
}
