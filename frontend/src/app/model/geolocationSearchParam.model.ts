export class GeolocationSearchParamModel{
  city:string
  radius: number


  constructor(city: string, radius: number) {
    this.city = city;
    this.radius = radius;
  }
}
