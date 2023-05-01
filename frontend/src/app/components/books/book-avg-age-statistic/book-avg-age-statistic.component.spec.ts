import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookAvgAgeStatisticComponent } from './book-avg-age-statistic.component';

describe('BookAvgAgeStatisticComponent', () => {
  let component: BookAvgAgeStatisticComponent;
  let fixture: ComponentFixture<BookAvgAgeStatisticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookAvgAgeStatisticComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookAvgAgeStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
