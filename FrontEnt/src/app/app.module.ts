import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';

import { registerLocaleData } from '@angular/common';
import localeFr from '@angular/common/locales/zh-Hans';

// the second parameter 'fr-FR' is optional
registerLocaleData(localeFr, 'zh');


@NgModule({
   declarations: [
      AppComponent,
      LoginComponent
   ],
   imports: [
      BrowserModule,
      NgbModule,
      AppRoutingModule,     
   ],
   providers: [],
   bootstrap: [
      AppComponent,
      LoginComponent
   ]
})
export class AppModule { 
}
