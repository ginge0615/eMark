import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SignupSellerComponent } from './signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './signup-buyer/signup-buyer.component';
import { ItemSearchComponent } from './item-search/item-search.component';
import { TopBarComponent } from './top-bar/top-bar.component';
import { BuyerMainComponent } from './buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './shopping-cart/shopping-cart.component';
import { PurchaseHistoryComponent } from './purchase-history/purchase-history.component';
import { SellerMainComponent } from './seller-main/seller-main.component';
import { AddItemComponent } from './add-item/add-item.component';

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
    component: SellerMainComponent,
    children: [
      { path: 'add-item', component: AddItemComponent },
      { path: 'manage-stock', component: PurchaseHistoryComponent },
      { path: 'reports', component: PurchaseHistoryComponent }
    ]
  },



];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
