import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SearchParameterModel} from "./model/searchParameter.model";
import {ApplicationModel} from "./model/application.model";
import {HttpService} from "./service/http.service";
import {GeolocationSearchParamModel} from "./model/geolocationSearchParam.model";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'Document management Platform';

  searchParameters: Array<SearchParameterModel> = [{
    id: Date.now().toString(),
    query: '',
    fieldName: '',
    isMustContain: false,
    isMustNotContain: false
  }];
  applications!: Array<ApplicationModel>;
  geoSearchParam!: GeolocationSearchParamModel;
  isGeolocationEnabled: boolean = false;


  constructor(private http: HttpClient, private router: Router, private _snackBar: MatSnackBar, private httpService: HttpService) {
    this.geoSearchParam = new GeolocationSearchParamModel("",0);
  }

  addSearchParameter() {
    this.searchParameters.push(new SearchParameterModel(Date.now().toString(), '', '', false, false))
  }

  delete(param: string) {
    let deleteIndex = 0;
    this.searchParameters.forEach((value, index) => {
      if (value.id == param) deleteIndex = index
    })

    if (this.searchParameters.length > 1) this.searchParameters.splice(deleteIndex, 1);
    else {
      this.searchParameters[0].isMustNotContain = false
      this.searchParameters[0].isMustContain = false
      this.searchParameters[0].fieldName = ''
      this.searchParameters[0].query = ''
    }
    let geoloc = null;
    if(this.isGeolocationEnabled){
      geoloc = this.geoSearchParam;
    }
    this.httpService.sendSearchRequest(this.searchParameters, geoloc).subscribe((response) => {
      this.applications = response;
    });

  }

  onParamChange($event: SearchParameterModel) {
    this.searchParameters.forEach((value,index)=>{
      if(value.id == $event.id){
        this.searchParameters[index] = $event

      }
    })
    let geoloc = null;
    if(this.isGeolocationEnabled){
      geoloc = this.geoSearchParam;
    }
    this.httpService.sendSearchRequest(this.searchParameters, geoloc).subscribe((response) => {
      this.applications = response;
    });
  }


  deleteGeo() {
    this.geoSearchParam.radius = 0;
    this.geoSearchParam.city = '';
    this.isGeolocationEnabled = false;
    this.httpService.sendSearchRequest(this.searchParameters, null).subscribe((response) => {
      this.applications = response;
    });
  }

  onGeoParamChange($event: GeolocationSearchParamModel) {
    this.httpService.sendSearchRequest(this.searchParameters, $event).subscribe((response) => {
      this.applications = response;
    });
  }
}
