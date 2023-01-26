import { getRole, Role } from "./role";

export class User{
    email: string;
    firstName: string;
    lastName: string;
    role: string;

    constructor(
        email: string,
        firstName: string,
        lastName: string,
        role: string)
    {
        this.role = role;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email
    }
}