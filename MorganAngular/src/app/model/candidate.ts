import { institution } from "./institution";
import { location } from "./location";

export interface candidate{
    candId : number,
    firstName : string,
    lastName : string,
    email : string,
    status: boolean,
    skillSet : object | any,
    locoData : location,
    instData : institution
}