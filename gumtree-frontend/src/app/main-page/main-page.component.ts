import { Component, OnInit } from '@angular/core';
import {CategoryService} from "../service/category.service";
import {Category} from "../model/category";

@Component({
  selector: 'app-main-page',
  templateUrl: './main-page.component.html',
  styleUrls: ['./main-page.component.scss']
})
export class MainPageComponent implements OnInit {

  mainCategories: Category[];
  constructor(private categoryService: CategoryService) { }

  ngOnInit(): void {
    this.categoryService
      .getMainCategotries()
      .subscribe(x => {
        this.mainCategories = x;
        console.log(x);
      });
  }

}
