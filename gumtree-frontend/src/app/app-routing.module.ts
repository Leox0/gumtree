import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MainPageComponent} from "./main-page/main-page.component";
import {CategoryPageComponent} from "./category-page/category-page.component";
import {EditOfferPageComponent} from "./edit-offer-page/edit-offer-page.component";

const routes: Routes = [
  {path:'', redirectTo:'main-page',pathMatch:'full'},
  {path:'main-page', component:MainPageComponent},
  {path:':categoryName', component:CategoryPageComponent},
  {path:':create-offer', component:EditOfferPageComponent},
  {path:':edit-offer/:offerId', component:EditOfferPageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
