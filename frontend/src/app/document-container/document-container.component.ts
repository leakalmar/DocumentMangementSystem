import {Component, Input} from '@angular/core';
import {ApplicationModel} from "../model/application.model";
import {HttpService} from "../service/http.service";

@Component({
  selector: 'app-document-container',
  templateUrl: './document-container.component.html',
  styleUrls: ['./document-container.component.scss']
})
export class DocumentContainerComponent {

  @Input("application") appplication!: ApplicationModel;


  constructor(private httpService: HttpService) {
  }

  onDownload(filename: string) {

    console.log(filename)
    this.httpService.downloadRequest(filename)
  }
}
