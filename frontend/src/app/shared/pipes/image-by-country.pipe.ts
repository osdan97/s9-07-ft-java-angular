import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'imageByCountry',
})
export class ImageByCountryPipe implements PipeTransform {
  private countryImages: { [country: string]: string } = {
    Argentina: '../../../../../../assets/images/argentina.svg',
    Colombia: '../../../../../../assets/images/colombia.svg',
    Mexico: '../../../../../../assets/images/mexico.svg',
    Venezuela: '../../../../../../assets/images/venezuela.svg',
  };

  transform(value: string): string {
    return this.countryImages[value] || '';
  }
}
