import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { MainPageComponent } from './main-page/main-page.component';
import { CategoryItemComponent } from './main-page/category-item/category-item.component';
import {HttpClientModule} from "@angular/common/http";
import { CategoryPageComponent } from './category-page/category-page.component';
import { EditOfferPageComponent } from './edit-offer-page/edit-offer-page.component';
import {ReactiveFormsModule} from "@angular/forms";
import {DatePipe} from "@angular/common";

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    MainPageComponent,
    CategoryItemComponent,
    CategoryPageComponent,
    EditOfferPageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [
    DatePipe
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
