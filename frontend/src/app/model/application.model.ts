export class ApplicationModel{
  name: string
  surname: string
  email: string
  address: string
  education: string
  phone: string
  cvContent: string
  coverLetterContent: string
  cvFilename: string
  coverLetterFilename: string


  constructor(firstname: string, lastname: string, email: string, address: string, education: string, phone: string, cvContent: string, letterContent: string, cvFilename: string, letterFilename: string) {
    this.name = firstname;
    this.surname = lastname;
    this.email = email;
    this.address = address;
    this.education = education;
    this.phone = phone;
    this.cvContent = cvContent;
    this.coverLetterContent = letterContent;
    this.cvFilename = cvFilename;
    this.coverLetterFilename = letterFilename;
  }
}
