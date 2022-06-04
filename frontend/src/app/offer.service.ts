import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Offer} from './offer';
import {environment} from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class OfferService {
    private apiServerUrl = environment.apiBaseUrl;

    constructor(private http: HttpClient) {
    }

    public getOffers(): Observable<Offer[]> {
        return this.http.get<Offer[]>(`${this.apiServerUrl}`);
    }

    public getOffer(offerId: string): Observable<Offer> {
        return this.http.get<Offer>(`${this.apiServerUrl}/${offerId}`);
    }

    public addOffer(offer: Offer): Observable<Offer> {
        offer.status = 'Prospect';
        return this.http.post<Offer>(`${this.apiServerUrl}`, offer);
    }

    public updateOffer(offer: Offer): Observable<Offer> {
        offer.status = 'Prospect';
        return this.http.put<Offer>(`${this.apiServerUrl}/${offer.id}`, offer);
    }

    public deleteOffer(offerId: string): Observable<void> {
        return this.http.delete<void>(`${this.apiServerUrl}/${offerId}`);
    }

    public acceptOffer(offer: Offer): Observable<Offer> {
        offer.status = 'Validated';
        return this.http.put<Offer>(`${this.apiServerUrl}/${offer.id}`, offer);
    }
}
