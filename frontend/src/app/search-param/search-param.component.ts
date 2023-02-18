import {Component, EventEmitter, Input, Output} from '@angular/core';
import {SearchParameterModel} from "../model/searchParameter.model";
import {FormControl} from "@angular/forms";

@Component({
  selector: 'app-search-param',
  templateUrl: './search-param.component.html',
  styleUrls: ['./search-param.component.scss']
})
export class SearchParamComponent {

  @Input("searchParam") searchParam!: SearchParameterModel;
  queryInput: FormControl;
  selectedField: FormControl;

  @Output('delete') delete: EventEmitter<string> = new EventEmitter<string>();


  constructor() {
    this.queryInput = new FormControl<string>('');
    this.selectedField = new FormControl<string>('');
  }


}
