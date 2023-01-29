// This file can be replaced during build by using the `fileReplacements` array.
// `ng build` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

const AUTORSKO_DELO_BASE_URL = 'http://localhost:8900/xml';
const USER_BASE_URL = 'http://localhost:8903/xml';
const ZIG_BASE_URL = "http://localhost:8902/xml";
const PATENT_BASE_URL = "http://localhost:8901/xml";
const PATENT_NAMESPACE = "http://www.ftn.uns.ac.rs/p1";
const COMMON_NAMSPACE =  "http://www.ftn.uns.ac.rs/zaj";
const AUTORSKO_DELO_NAMESPACE = "http://ftn.uns.ac.rs/a1";
const ZIG_NAMESPACE = "http://ftn.uns.ac.rs/zig"


export const environment = {
  production: false,

  AUTORSKO_DELO_BASE_URL: AUTORSKO_DELO_BASE_URL,
  USER_BASE_URL: USER_BASE_URL,
  ZIG_BASE_URL: ZIG_BASE_URL,
  PATENT_BASE_URL: PATENT_BASE_URL,
  PATENT_NAMESPACE: PATENT_NAMESPACE,
  COMMON_NAMSPACE: COMMON_NAMSPACE,
  AUTORSKO_DELO_NAMESPACE: AUTORSKO_DELO_NAMESPACE,
   ZIG_NAMESPACE:ZIG_NAMESPACE
};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/plugins/zone-error';  // Included with Angular CLI.
