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
  @Output('change') change: EventEmitter<SearchParameterModel> = new EventEmitter<SearchParameterModel>();
  isMustContain: FormControl;
  isMustNotContain: FormControl;


  constructor() {
    this.queryInput = new FormControl<string>('');
    this.selectedField = new FormControl<string>('');
    this.isMustContain = new FormControl<boolean>(false);
    this.isMustNotContain = new FormControl<boolean>(false);

    this.queryInput.valueChanges.subscribe((value) => {
      this.searchParam.query = value;
      this.change.emit(this.searchParam)
    })
    this.selectedField.valueChanges.subscribe((value) => {
      this.searchParam.fieldName = value;
      this.change.emit(this.searchParam);
    })
    this.isMustContain.valueChanges.subscribe((value) => {
      this.searchParam.isMustContain = value;
      this.change.emit(this.searchParam);
    })
    this.isMustNotContain.valueChanges.subscribe((value) => {
      this.searchParam.isMustNotContain = value;
      this.change.emit(this.searchParam);
    })
  }


}
