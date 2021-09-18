import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {DatePipe} from "@angular/common"
import {ActivatedRoute, Params} from "@angular/router";
import {OffersService} from "../service/offers.service";

@Component({
  selector: 'app-edit-offer-page',
  templateUrl: './edit-offer-page.component.html',
  styleUrls: ['./edit-offer-page.component.scss']
})
export class EditOfferPageComponent implements OnInit {

  editOfferFormGroup: FormGroup

  offer: { title: string, price: number, publishDate: string, content: string } =
    {'title': 'Piewsze ogloszenie', 'price': 50, 'publishDate': '2021-09-18', 'content': 'bla'};

  constructor(private dataPipe: DatePipe, private route: ActivatedRoute, private offerService: OffersService) {
  }

  ngOnInit(): void {

    this.editOfferFormGroup = new FormGroup({
      'offerData': new FormGroup({
        'title': new FormControl('', Validators.required),
        'price': new FormControl('0'),
        'publishDate': new FormControl(this.dataPipe.transform(new Date(), 'yyyy-MM-dd')),
        'content': new FormControl('')
      })
    });

    this.route.params.subscribe((param: Params) => {
        let offerId = param['offerId'];
        this.offerService.getOfferById(offerId).subscribe(offer => {
          this.offer = offer;
          this.editOfferFormGroup.setValue({'offerData':this.offer})
        })
      });

    this.editOfferFormGroup.setValue({'offerData': this.offer});

    let formContent = this.editOfferFormGroup.getRawValue();
  }

  onSubmit($event: any) {
    let rawValue = this.editOfferFormGroup.getRawValue();
    console.log(rawValue);

  }
}
