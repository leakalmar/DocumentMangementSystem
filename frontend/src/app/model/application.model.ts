export class ApplicationModel{
  firstname: string
  lastname: string
  email: string
  address: string
  education: string
  phone: string
  cvContent: string
  letterContent: string
  cvFilename: string
  letterFilename: string


  constructor(firstname: string, lastname: string, email: string, address: string, education: string, phone: string, cvContent: string, letterContent: string, cvFilename: string, letterFilename: string) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.address = address;
    this.education = education;
    this.phone = phone;
    this.cvContent = cvContent;
    this.letterContent = letterContent;
    this.cvFilename = cvFilename;
    this.letterFilename = letterFilename;
  }
}
