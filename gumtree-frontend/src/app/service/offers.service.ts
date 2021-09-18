import { Injectable } from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";

type Offer =  { title: string, price: number, publishDate: string, content: string };


@Injectable({
  providedIn: 'root'
})
export class OffersService {

  constructor(private httpClient: HttpClient) { }

  getOfferById(offerId: string): Observable<Offer>{
    return this.httpClient.get<Offer>('http://localhost:8080/api/offers/' + offerId);
  }
}
