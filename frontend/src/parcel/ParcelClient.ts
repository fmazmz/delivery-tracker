import {Parcel} from "./Parcel"

class ParcelClient {
    baseUrl: string;

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    async getParcel(trackingNumber: string): Promise<Parcel> {
        const fullUrl = `${this.baseUrl}/api/v1/parcels/${trackingNumber}`;
        let response;

        try {
            response = await fetch(fullUrl, {
                headers: {
                    Accept: "application/json"
                }
            });
        } catch (e) {
            throw new Error("Failed to fetch data");
        }

        if (!response.ok) {
            throw new Error(`Http error: ${response.status}`)
        }
        if (response.status = 404) {
            throw new Error(`Parcel with tracking number: ${trackingNumber} not found`)
        }

        const data = await response.json();
        console.log(`Received API response: ${data}`);

        return new Parcel(
            data.trackingNumber,
            data.status,
            data.location,
            new Date(data.updatedAt)
        );
    }
}