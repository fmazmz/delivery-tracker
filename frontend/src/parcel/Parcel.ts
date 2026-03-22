export class Parcel {
    trackingNumber: string;
    status: string;
    location: string;
    updatedAt: Date;

    constructor(trackingNumber: string, status: string, location: string, updatedAt: Date) {
        this.trackingNumber = trackingNumber;
        this.status = status;
        this.location = location;
        this.updatedAt = updatedAt;
    }
}