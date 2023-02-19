import {Component, EventEmitter, Input, Output} from '@angular/core';
import {SearchParameterModel} from "../model/searchParameter.model";
import {FormControl} from "@angular/forms";
import {GeolocationSearchParamModel} from "../model/geolocationSearchParam.model";

@Component({
  selector: 'app-search-param-geolocation',
  templateUrl: './search-param-geolocation.component.html',
  styleUrls: ['./search-param-geolocation.component.scss']
})
export class SearchParamGeolocationComponent {

  @Input("searchParam") searchParam!: GeolocationSearchParamModel;

  @Output('delete') delete: EventEmitter<void> = new EventEmitter<void>();
  @Output('change') change: EventEmitter<GeolocationSearchParamModel> = new EventEmitter<GeolocationSearchParamModel>();

  cityInput: FormControl;
  radiusInput: FormControl;


  constructor() {
    this.cityInput = new FormControl<string>('');
    this.radiusInput = new FormControl<string>('');

    this.cityInput.valueChanges.subscribe((value) => {
      this.searchParam.city = value;
      this.change.emit(this.searchParam)
    })
    this.radiusInput.valueChanges.subscribe((value) => {
      this.searchParam.radius = value;
      this.change.emit(this.searchParam);
    })

  }
}
