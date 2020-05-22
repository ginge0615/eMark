import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { LoginComponent } from './component/common/login/login.component';
import { SignupSellerComponent } from './component/seller/signup-seller/signup-seller.component';
import { SignupBuyerComponent } from './component/buyer/signup-buyer/signup-buyer.component';
import { BuyerMainComponent } from './component/buyer/buyer-main/buyer-main.component';
import { ItemSpecificationsComponent } from './component/buyer/item-specifications/item-specifications.component';
import { ShoppingCartComponent } from './component/buyer/shopping-cart/shopping-cart.component';
import { SellerMainComponent } from './component/seller/seller-main/seller-main.component';
import { ServerErrorComponent } from  './component/common/server-error/server-error.component'
import { SignupSuccessComponent } from './component/common/signup-success/signup-success.component'

import { AuthGuard } from './services/auth.guard';

const routes: Routes = [
  { path: '', redirectTo : 'emart-buyer',pathMatch:'full' },
  { path: 'emart-buyer', component: BuyerMainComponent },
  { path: 'emart-seller', component: SellerMainComponent,canActivate: [AuthGuard] },
  { path: 'login', component: LoginComponent },
  { path: 'singup-buyer', component: SignupBuyerComponent },
  { path: 'singup-seller', component: SignupSellerComponent },
  { path: 'items-pecifications/:id', component: ItemSpecificationsComponent },
  { path: 'cart', component: ShoppingCartComponent, canActivate: [AuthGuard] },
  { path: 'singup-success', component: SignupSuccessComponent},
  { path: 'server-error/:code', component: ServerErrorComponent},
  { path: '**', component: ServerErrorComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
