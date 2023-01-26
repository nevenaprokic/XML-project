export class Error{
    status: number;
    message: string;

    constructor( status: number,
        message: string){
        this.message = message;
        this.status = status;
    }
}