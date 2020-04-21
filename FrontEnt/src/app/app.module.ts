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


//MyCompents
import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';

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
      RouterModule.forRoot([
         { path: '', component: ShoppingCartComponent },
      ]),
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
   ],
   providers: [],
   bootstrap: [
      AppComponent
   ]
})
export class AppModule {
}
