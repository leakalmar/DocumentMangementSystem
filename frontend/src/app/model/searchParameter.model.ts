export class SearchParameterModel{
  id: string
  fieldName: string
  query: string

  isMustContain: boolean

  isMustNotContain: boolean


  constructor(id: string, fieldName: string, query: string, isMustContain: boolean, isMustNotContain: boolean) {
    this.id = id;
    this.fieldName = fieldName;
    this.query = query;
    this.isMustContain = isMustContain;
    this.isMustNotContain = isMustNotContain;
  }
}
