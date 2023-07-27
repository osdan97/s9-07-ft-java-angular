import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SearchResultRoutingModule } from './search-result-routing.module';
import { SearchResultComponent } from './search-result.component';
import { SharedModule } from 'src/app/shared/shared.module';

@NgModule({
  declarations: [SearchResultComponent],
  imports: [CommonModule, SearchResultRoutingModule, SharedModule],
})
export class SearchResultModule {}
