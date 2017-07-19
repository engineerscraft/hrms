import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';
import { ActivatedRoute, Router, Params } from '@angular/router';

@Component({
  selector: 'app-employee-details',
  templateUrl: './employee-details.component.html',
  styleUrls: ['./employee-details.component.css'],
  providers: [EmployeeService]
})
export class EmployeeDetailsComponent implements OnInit {

  private employeeInfo;
  private id;
  private profileImage;

  constructor(private employeeService: EmployeeService, private activatedRoute: ActivatedRoute, private router: Router) {

  }

  ngOnInit() {
    this.id = this.activatedRoute
      .queryParams
      .subscribe(params => {
        this.id = params['id'];
        this.employeeService.read(this.id)
          .subscribe(data => {
            this.employeeInfo = data;
          },
          (err: any) => {
            if (err.status === 401) {
              this.router.navigate(['forbidden']);
            }
            if (err.status === 404) {
              this.router.navigate(['404']);
            }
          },
          () => {

          });
      });
  }
}
