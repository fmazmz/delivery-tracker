import {Parcel} from "./Parcel"

export class ParcelClient {
    baseUrl: string;

    constructor(baseUrl: string) {
        this.baseUrl = baseUrl;
    }

    async getParcel(trackingNumber: string): Promise<Parcel> {
        const fullUrl = `${this.baseUrl}/api/v1/parcels/track/${trackingNumber}`;
        let response;

        try {
            response = await fetch(fullUrl, {
                headers: {
                    Accept: "application/json"
                }
            });
            console.log(response);
        } catch (e) {
            throw new Error("Failed to fetch data");
        }

        if (response.status === 404) {
            throw new Error(`Parcel with tracking number: ${trackingNumber} not found`)
        }

        if (!response.ok) {
            throw new Error(`Http error: ${response.status}`)
        }

        const raw = await response.json();
        console.log(`Received API response: ${raw}`);

        return new Parcel(
            raw.data.trackingNumber,
            raw.data.status,
            raw.data.location,
            new Date(raw.data.updatedAt)
        );
    }
}