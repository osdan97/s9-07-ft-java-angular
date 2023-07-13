import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-content-card',
  templateUrl: './content-card.component.html',
  styleUrls: ['./content-card.component.scss'],
})
export class ContentCardComponent {
  @Input() products!: any[];
  paginate(event: any) {
    event.first = 0;
    event.rows = 5;
    event.page = 1;
    event.pageCount = 120;
  }
}
