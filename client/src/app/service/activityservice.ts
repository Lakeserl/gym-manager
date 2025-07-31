import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

export interface ActivityData {
  id?: number;
  date: string;
  step: number;
  distance: number;
  caloriesBurned: number;
}

const baseUrl = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class Activityservice {

  constructor(private http: HttpClient) {}

  postActivity(activityDTO: any): Observable<any>{
    return this.http.post(baseUrl + "test/activity", activityDTO);
  }

  getActivity(): Observable<any>{
    return this.http.get(baseUrl + "test/activities");
  }
}
