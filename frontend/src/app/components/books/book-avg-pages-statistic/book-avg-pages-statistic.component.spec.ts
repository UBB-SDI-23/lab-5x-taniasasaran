import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BookAvgPagesStatisticComponent } from './book-avg-pages-statistic.component';

describe('BookAvgPagesStatisticComponent', () => {
  let component: BookAvgPagesStatisticComponent;
  let fixture: ComponentFixture<BookAvgPagesStatisticComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BookAvgPagesStatisticComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(BookAvgPagesStatisticComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
