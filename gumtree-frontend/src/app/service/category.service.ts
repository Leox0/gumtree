import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Category} from "../model/category";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  host = 'http://localhost:8080';
  constructor(private  httpClient: HttpClient) {  }

  public getMainCategotries(): Observable<Category[]>{
    return this.httpClient.get<Category[]>(this.host + '/categories')
  }

  public getSubcategoriesFor(categoryname: string): Observable<Category>{
    return this.httpClient.get<Category>(this.host + '/categories/' + categoryname)
  }
}
