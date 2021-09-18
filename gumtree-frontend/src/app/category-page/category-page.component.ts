import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params} from "@angular/router";
import {Category} from "../model/category";
import {CategoryService} from "../service/category.service";

@Component({
  selector: 'app-category-page',
  templateUrl: './category-page.component.html',
  styleUrls: ['./category-page.component.scss']
})
export class CategoryPageComponent implements OnInit {

  category: Category;

  constructor(private activeRoute: ActivatedRoute,
              private categoryService: CategoryService) {
  }

  ngOnInit(): void {
    this.activeRoute.params.subscribe((params: Params) => {
        let categoryName: string = params['categoryName'];
        this.categoryService.getSubcategoriesFor(categoryName)
          .subscribe(category => this.category = category);
      }
    )
  }
}
