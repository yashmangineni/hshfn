import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ServiceService {
  updateUserState(userData: { email: string; }) {
    throw new Error('Method not implemented.');
  }

  private userState = new BehaviorSubject<any>(null);
  constructor(private _http:HttpClient) { 

    const savedUser = localStorage.getItem('user');
    if (savedUser) {
      this.userState.next(JSON.parse(savedUser));
    }
  }

private url="http://localhost:8080/insert";
  private urll="http://localhost:8080/sign/";

  
  public adduser(data):Observable<any>
  {
    return this._http.post(this.url,data);
  }
  public login(email: any, password: any): Observable<string> {
    return this._http.get(`${this.urll}${email}/${password}`, { responseType: 'text' });
  }



  private apiUrl = 'http://localhost:8080/api/employees';
  addEmployee(data: FormData) {
    return this._http.post(this.apiUrl, data);
  }


  private baseUrl = 'http://localhost:8080/api/employees';

searchEmployees(keyword: string, page: number, size: number): Observable<any> {
  return this._http.get<any>(`${this.baseUrl}/search`, {
    params: {
      keyword,
      page,
      size
    }
  });
}


  getEmployeeById(id: number): Observable<any> {
    return this._http.get<any>(`${this.baseUrl}/${id}`);
  }

 updateEmployee(id: number, employeeData: any): Observable<any> {
  return this._http.put<any>(`${this.baseUrl}/${id}`, employeeData);
}
deleteEmployee(id: number): Observable<any> {
  return this._http.delete(`${this.baseUrl}/${id}`);
}

getAllEmployees(): Observable<any[]> {
    return this._http.get<any[]>(`${this.baseUrl}/all`);
  }


}
