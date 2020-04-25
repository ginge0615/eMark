import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { SignupSellerComponent } from './seller/signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './buyer/signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './buyer/item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer/buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './buyer/item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './buyer/shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './buyer/purchase-history/purchase-history.component';
import { SellerMainComponent } from './seller/seller-main/seller-main.component';
import { AddItemComponent } from './seller/add-item/add-item.component';
import { ManageStockComponent } from './seller/manage-stock/manage-stock.component'

const routes: Routes = [
  // {
  //   path: '',
  //   component: BuyerMainComponent,
  //   children: [
  //     { path: 'search-item', component: ItemSearchComponent },
  //     { path: 'purchase-history/:userid', component: PurchaseHistoryComponent }
  //   ]
  // },
  { path: 'items-pecifications/:id', component: ItemSpecificationsComponent },
  {
    path: '',
    component: SellerMainComponent
    ,
    children: [
      { path: 'add-item', component: AddItemComponent },
      { path: 'manage-stock', component: ManageStockComponent },
      { path: 'reports', component: PurchaseHistoryComponent }
    ]
  },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
