import { Component, OnInit } from '@angular/core';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css'],
  providers: [EmployeeService]
})
export class UserDetailsComponent implements OnInit {

  private employeeId = localStorage.getItem("userName");
  private employeeInfo;

  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
    this.employeeService.read(this.employeeId)
    .subscribe(data => {
      this.employeeInfo = data;
      console.log("Employee Service Read Response : "+JSON.stringify(this.employeeInfo));
    });
  }

}
