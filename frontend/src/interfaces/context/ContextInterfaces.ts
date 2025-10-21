import React from "react";
import type {Week} from "../pages/PageInterfaces.ts";

export interface DateContextProps {
    year: number;
    setYear: React.Dispatch<React.SetStateAction<number>>;
    month: number;
    setMonth: React.Dispatch<React.SetStateAction<number>>;
    weeks: Week[];
}