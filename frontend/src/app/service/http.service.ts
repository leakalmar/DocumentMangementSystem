import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {ApplicationModel} from "../model/application.model";
import {SearchParameterModel} from "../model/searchParameter.model";
import {map} from "rxjs";
import {GeolocationSearchParamModel} from "../model/geolocationSearchParam.model";
@Injectable({
  providedIn: 'root',
})
export class HttpService{


  constructor(private http: HttpClient) {
  }
  public sendSearchRequest(searchParameters: Array<SearchParameterModel>, geoloc: GeolocationSearchParamModel | null) {
    let request :any = {fields: searchParameters, geolocationField: geoloc};
    if(geoloc == null){
      request = {fields: searchParameters};
    }
    if(searchParameters.length == 1 && searchParameters[0].query== "") {
      request.fields = []
    }
    return this.http.post<Array<ApplicationModel>>("http://localhost:9000/search", request);

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
