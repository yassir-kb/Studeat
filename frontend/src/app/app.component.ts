import {Component, OnInit} from '@angular/core';
import {Offer} from './offer';
import {OfferService} from './offer.service';
import {HttpErrorResponse} from '@angular/common/http';
import {NgForm} from '@angular/forms';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    public offers: Offer[];
    public editOffer: Offer;
    public deleteOffer: Offer;
    public acceptOffer: Offer;
    title: 'Studeat';

    constructor(private offerService: OfferService) {
    }

    ngOnInit() {
        this.getOffers();
    }

    public getOffers(): void {
        this.offerService.getOffers().subscribe(
            (response: Offer[]) => {
                this.offers = response;
                console.log(this.offers);
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

    public onAddEmloyee(addForm: NgForm): void {
        document.getElementById('add-offer-form').click();
        this.offerService.addOffer(addForm.value).subscribe(
            (response: Offer) => {
                console.log(response);
                this.getOffers();
                addForm.reset();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
                addForm.reset();
            }
        );
    }

    public onUpdateEmloyee(offer: Offer): void {
        this.offerService.updateOffer(offer).subscribe(
            (response: Offer) => {
                console.log(response);
                this.getOffers();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

    public onDeleteEmloyee(offerId: string | undefined): void {
        this.offerService.deleteOffer(offerId).subscribe(
            (response: void) => {
                console.log(response);
                this.getOffers();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

    public onAcceptEmloyee(offer: Offer): void {
        this.offerService.acceptOffer(offer).subscribe(
            (response: Offer) => {
                console.log(response);
                this.getOffers();
            },
            (error: HttpErrorResponse) => {
                alert(error.message);
            }
        );
    }

    public searchOffers(key: string): void {
        const results: Offer[] = [];
        for (const offer of this.offers) {
            if (offer.id.toLowerCase().indexOf(key.toLowerCase()) !== -1
                || offer.title.toLowerCase().indexOf(key.toLowerCase()) !== -1
                || offer.restauName.toLowerCase().indexOf(key.toLowerCase()) !== -1
                || offer.localisation.toLowerCase().indexOf(key.toLowerCase()) !== -1
            ) {
                results.push(offer);
            }
        }
        this.offers = results;
        if (results.length === 0 || !key) {
            this.getOffers();
        }
    }

    public onOpenModal(offer: Offer, mode: string): void {
        const container = document.getElementById('main-container');
        const button = document.createElement('button');
        button.type = 'button';
        button.style.display = 'none';
        button.setAttribute('data-toggle', 'modal');
        if (mode === 'add') {
            button.setAttribute('data-target', '#addOfferModal');
        }
        if (mode === 'edit') {
            this.editOffer = offer;
            button.setAttribute('data-target', '#updateOfferModal');
        }
        if (mode === 'delete') {
            this.deleteOffer = offer;
            button.setAttribute('data-target', '#deleteOfferModal');
        }
        if (mode === 'accept') {
            this.acceptOffer = offer;
            button.setAttribute('data-target', '#acceptOfferModal');
        }
        container.appendChild(button);
        button.click();
    }
}
