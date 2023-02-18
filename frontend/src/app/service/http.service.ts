import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ApplicationModel} from "../model/application.model";
import {SearchParameterModel} from "../model/searchParameter.model";
import {map} from "rxjs";
@Injectable({
  providedIn: 'root',
})
export class HttpService{


  constructor(private http: HttpClient) {
  }
  public sendSearchRequest(searchParameters: Array<SearchParameterModel>) {
    return this.http.post<Array<ApplicationModel>>("http://localhost:9000/search", {fields: searchParameters});
  }

  public downloadRequest(fileName: string){
    let headerOptions = new HttpHeaders({
      'Content-Type': 'application/json',
      'Accept': 'application/pdf',
    });
    let requestOptions = { headers: headerOptions, responseType: 'blob' as 'blob' };
    this.http.get("http://localhost:9000/download/" + fileName, requestOptions).pipe(map((data: any) => {
      let blob = new Blob([data], {
        type: 'application/pdf'
      });
      var link = document.createElement('a');
      link.href = window.URL.createObjectURL(blob);
      link.target = '_blank';
      link.click();
      window.URL.revokeObjectURL(link.href);

    })).subscribe(() => {});
  }
}
