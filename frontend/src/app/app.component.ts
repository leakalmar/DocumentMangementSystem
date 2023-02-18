import {Component} from '@angular/core';
import {FormControl} from "@angular/forms";
import {HttpClient} from "@angular/common/http";
import {Router} from "@angular/router";
import {MatSnackBar} from "@angular/material/snack-bar";
import {SearchParameterModel} from "./model/searchParameter.model";

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
  selectedField: FormControl = new FormControl<string>('');
  query: FormControl = new FormControl<string>('');
  mustContain: FormControl = new FormControl<boolean>(false);
  mustNotContain: FormControl = new FormControl<boolean>(false);

  constructor(private http: HttpClient, private router: Router, private _snackBar: MatSnackBar) {
  }

  onBuy(amount: number, billingCycle: string) {
    // if(this.apiKey.value){
    //   this.http.post("http://192.168.43.124:9000/auth-service/payment-attempts/", {apiKey: this.apiKey.value ,amount: amount, billingCycle: billingCycle}, { responseType: 'text' as const}).subscribe(response =>{
    //     window.location.href = response;
    //   })
    // }else{
    //   this._snackBar.open("Please fill out your API Key!", "",{duration: 2000})
    // }
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

  }
}
