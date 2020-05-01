import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './login/login.component';
import { SignupSellerComponent } from './seller/signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './buyer/signup-buyer/signup-buyer.component';
import { BuyerMainComponent } from './buyer/buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './buyer/item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './buyer/shopping-cart/shopping-cart.component';
import { SellerMainComponent } from './seller/seller-main/seller-main.component';

import { AuthGuard } from 'src/app/auth/auth.guard';

const routes: Routes = [
  { path: '', redirectTo : 'emart-buyer',pathMatch:'full' },
  { path: 'emart-buyer', component: BuyerMainComponent },
  { path: 'emart-seller', component: SellerMainComponent,canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'singup-buyer', component: SignupBuyerComponent },
  { path: 'singup-seller', component: SignupSellerComponent },
  { path: 'items-pecifications/:id', component: ItemSpecificationsComponent },
  { path: 'cart', component: ShoppingCartComponent, canActivate: [AuthGuard] },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
