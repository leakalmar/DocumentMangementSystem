import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SearchParameterModel} from "./model/searchParameter.model";
import {ApplicationModel} from "./model/application.model";
import {HttpService} from "./service/http.service";

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


  constructor(private http: HttpClient, private router: Router, private _snackBar: MatSnackBar, private httpService: HttpService) {
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
    this.httpService.sendSearchRequest(this.searchParameters).subscribe((response) => {
      this.applications = response;
    });

  }

  onParamChange($event: SearchParameterModel) {
    this.searchParameters.forEach((value,index)=>{
      if(value.id == $event.id){
        this.searchParameters[index] = $event

      }
    })
    this.httpService.sendSearchRequest(this.searchParameters).subscribe((response) => {
      this.applications = response;
    });
  }


}
