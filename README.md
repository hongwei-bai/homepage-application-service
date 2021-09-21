# homepage-application-service
homepage-application-service

Homepage application is a backend service providing basic apis for my personal homepage.

The frontend repository is [hongwei-bai/react-homepage-demo](https://github.com/hongwei-bai/react-homepage-demo).

Homepage application only provides services can be accessed by public token.

Those services need finer controlled access (e.g. blog, albums etc.) would be provided by other applications.

Public access token is generated with same secrets and shared across all frontend clients.(web/Android/iOS)

A sample response is like:

@GET /covid/au/raw.do?days=1&dataVersion=0

```
{
    "dataVersion": 2021092118,
    "lastUpdate": "21 September 2021",
    "recordsCount": 53709,
    "lastRecordDate": "2021-09-20",
    "dataByDay": [
        {
            "date": 1632067200000,
            "caseByState": [
                {
                    "stateCode": "NSW",
                    "stateName": "New South Wales",
                    "cases": 898
                }
            ],
            "caseExcludeFromStates": 14,
            "caseTotal": 912,
            "caseByPostcode": [
                {
                    "postcode": 2170,
                    "cases": 65
                },
                {
                    "postcode": 2770,
                    "cases": 51
                },
                {
                    "postcode": 2200,
                    "cases": 31
                },
                {
                    "postcode": 2190,
                    "cases": 28
                },
                {
                    "postcode": 2161,
                    "cases": 20
                },
                {
                    "postcode": 2160,
                    "cases": 20
                },
                {
                    "postcode": 2212,
                    "cases": 19
                },
                {
                    "postcode": 2145,
                    "cases": 18
                },
                {
                    "postcode": 2165,
                    "cases": 17
                },
                {
                    "postcode": 2567,
                    "cases": 16
                },
                {
                    "postcode": 2148,
                    "cases": 16
                },
                {
                    "postcode": 2168,
                    "cases": 16
                },
                {
                    "postcode": 2144,
                    "cases": 15
                },
                {
                    "postcode": 2142,
                    "cases": 14
                },
                {
                    "postcode": 2196,
                    "cases": 14
                },
                {
                    "postcode": 0,
                    "cases": 14
                },
                {
                    "postcode": 2166,
                    "cases": 14
                },
                {
                    "postcode": 2560,
                    "cases": 13
                },
                {
                    "postcode": 2141,
                    "cases": 13
                },
                {
                    "postcode": 2760,
                    "cases": 12
                },
                {
                    "postcode": 2017,
                    "cases": 12
                },
                {
                    "postcode": 2199,
                    "cases": 11
                },
                {
                    "postcode": 2036,
                    "cases": 11
                },
                {
                    "postcode": 2211,
                    "cases": 10
                },
                {
                    "postcode": 2766,
                    "cases": 10
                },
                {
                    "postcode": 2195,
                    "cases": 9
                },
                {
                    "postcode": 2162,
                    "cases": 9
                },
                {
                    "postcode": 2167,
                    "cases": 9
                },
                {
                    "postcode": 2035,
                    "cases": 9
                },
                {
                    "postcode": 2198,
                    "cases": 8
                },
                {
                    "postcode": 2747,
                    "cases": 8
                },
                {
                    "postcode": 2147,
                    "cases": 7
                },
                {
                    "postcode": 2541,
                    "cases": 7
                },
                {
                    "postcode": 2263,
                    "cases": 7
                },
                {
                    "postcode": 2163,
                    "cases": 7
                },
                {
                    "postcode": 2765,
                    "cases": 7
                },
                {
                    "postcode": 2099,
                    "cases": 7
                },
                {
                    "postcode": 2565,
                    "cases": 6
                },
                {
                    "postcode": 2000,
                    "cases": 6
                },
                {
                    "postcode": 2176,
                    "cases": 6
                },
                {
                    "postcode": 2213,
                    "cases": 6
                },
                {
                    "postcode": 2010,
                    "cases": 6
                },
                {
                    "postcode": 2210,
                    "cases": 5
                },
                {
                    "postcode": 2192,
                    "cases": 5
                },
                {
                    "postcode": 2229,
                    "cases": 5
                },
                {
                    "postcode": 2594,
                    "cases": 5
                },
                {
                    "postcode": 2135,
                    "cases": 5
                },
                {
                    "postcode": 2031,
                    "cases": 5
                },
                {
                    "postcode": 2220,
                    "cases": 5
                },
                {
                    "postcode": 2234,
                    "cases": 5
                },
                {
                    "postcode": 2164,
                    "cases": 4
                },
                {
                    "postcode": 2830,
                    "cases": 4
                },
                {
                    "postcode": 2767,
                    "cases": 4
                },
                {
                    "postcode": 2021,
                    "cases": 4
                },
                {
                    "postcode": 2026,
                    "cases": 4
                },
                {
                    "postcode": 2566,
                    "cases": 4
                },
                {
                    "postcode": 2750,
                    "cases": 4
                },
                {
                    "postcode": 2107,
                    "cases": 4
                },
                {
                    "postcode": 2836,
                    "cases": 4
                },
                {
                    "postcode": 2221,
                    "cases": 4
                },
                {
                    "postcode": 2259,
                    "cases": 4
                },
                {
                    "postcode": 2204,
                    "cases": 4
                },
                {
                    "postcode": 2557,
                    "cases": 4
                },
                {
                    "postcode": 2016,
                    "cases": 4
                },
                {
                    "postcode": 2153,
                    "cases": 4
                },
                {
                    "postcode": 2173,
                    "cases": 4
                },
                {
                    "postcode": 2570,
                    "cases": 4
                },
                {
                    "postcode": 2205,
                    "cases": 4
                },
                {
                    "postcode": 2527,
                    "cases": 4
                },
                {
                    "postcode": 2571,
                    "cases": 4
                },
                {
                    "postcode": 2756,
                    "cases": 4
                },
                {
                    "postcode": 2261,
                    "cases": 4
                },
                {
                    "postcode": 2112,
                    "cases": 3
                },
                {
                    "postcode": 2322,
                    "cases": 3
                },
                {
                    "postcode": 2113,
                    "cases": 3
                },
                {
                    "postcode": 2762,
                    "cases": 3
                },
                {
                    "postcode": 2034,
                    "cases": 3
                },
                {
                    "postcode": 2015,
                    "cases": 3
                },
                {
                    "postcode": 2191,
                    "cases": 3
                },
                {
                    "postcode": 2155,
                    "cases": 3
                },
                {
                    "postcode": 2564,
                    "cases": 3
                },
                {
                    "postcode": 2283,
                    "cases": 3
                },
                {
                    "postcode": 2582,
                    "cases": 3
                },
                {
                    "postcode": 2032,
                    "cases": 3
                },
                {
                    "postcode": 2216,
                    "cases": 3
                },
                {
                    "postcode": 2518,
                    "cases": 3
                },
                {
                    "postcode": 2197,
                    "cases": 3
                },
                {
                    "postcode": 2009,
                    "cases": 3
                },
                {
                    "postcode": 2232,
                    "cases": 3
                },
                {
                    "postcode": 2122,
                    "cases": 3
                },
                {
                    "postcode": 2114,
                    "cases": 2
                },
                {
                    "postcode": 2077,
                    "cases": 2
                },
                {
                    "postcode": 2753,
                    "cases": 2
                },
                {
                    "postcode": 2194,
                    "cases": 2
                },
                {
                    "postcode": 2290,
                    "cases": 2
                },
                {
                    "postcode": 2206,
                    "cases": 2
                },
                {
                    "postcode": 2209,
                    "cases": 2
                },
                {
                    "postcode": 2620,
                    "cases": 2
                },
                {
                    "postcode": 2177,
                    "cases": 2
                },
                {
                    "postcode": 2037,
                    "cases": 2
                },
                {
                    "postcode": 2208,
                    "cases": 2
                },
                {
                    "postcode": 2140,
                    "cases": 2
                },
                {
                    "postcode": 2761,
                    "cases": 2
                },
                {
                    "postcode": 2018,
                    "cases": 2
                },
                {
                    "postcode": 2131,
                    "cases": 2
                },
                {
                    "postcode": 2250,
                    "cases": 2
                },
                {
                    "postcode": 2540,
                    "cases": 2
                },
                {
                    "postcode": 2526,
                    "cases": 2
                },
                {
                    "postcode": 2230,
                    "cases": 2
                },
                {
                    "postcode": 2749,
                    "cases": 2
                },
                {
                    "postcode": 2095,
                    "cases": 2
                },
                {
                    "postcode": 2529,
                    "cases": 2
                },
                {
                    "postcode": 2127,
                    "cases": 2
                },
                {
                    "postcode": 2179,
                    "cases": 2
                },
                {
                    "postcode": 2763,
                    "cases": 2
                },
                {
                    "postcode": 2100,
                    "cases": 2
                },
                {
                    "postcode": 2137,
                    "cases": 2
                },
                {
                    "postcode": 2207,
                    "cases": 2
                },
                {
                    "postcode": 2150,
                    "cases": 2
                },
                {
                    "postcode": 2151,
                    "cases": 2
                },
                {
                    "postcode": 2120,
                    "cases": 1
                },
                {
                    "postcode": 2050,
                    "cases": 1
                },
                {
                    "postcode": 2027,
                    "cases": 1
                },
                {
                    "postcode": 2044,
                    "cases": 1
                },
                {
                    "postcode": 2071,
                    "cases": 1
                },
                {
                    "postcode": 2134,
                    "cases": 1
                },
                {
                    "postcode": 2580,
                    "cases": 1
                },
                {
                    "postcode": 2065,
                    "cases": 1
                },
                {
                    "postcode": 2186,
                    "cases": 1
                },
                {
                    "postcode": 2370,
                    "cases": 1
                },
                {
                    "postcode": 2030,
                    "cases": 1
                },
                {
                    "postcode": 2101,
                    "cases": 1
                },
                {
                    "postcode": 2008,
                    "cases": 1
                },
                {
                    "postcode": 2572,
                    "cases": 1
                },
                {
                    "postcode": 2731,
                    "cases": 1
                },
                {
                    "postcode": 2158,
                    "cases": 1
                },
                {
                    "postcode": 2118,
                    "cases": 1
                },
                {
                    "postcode": 2299,
                    "cases": 1
                },
                {
                    "postcode": 2487,
                    "cases": 1
                },
                {
                    "postcode": 2581,
                    "cases": 1
                },
                {
                    "postcode": 2007,
                    "cases": 1
                },
                {
                    "postcode": 2284,
                    "cases": 1
                },
                {
                    "postcode": 2143,
                    "cases": 1
                },
                {
                    "postcode": 2094,
                    "cases": 1
                },
                {
                    "postcode": 2126,
                    "cases": 1
                },
                {
                    "postcode": 2777,
                    "cases": 1
                },
                {
                    "postcode": 2768,
                    "cases": 1
                },
                {
                    "postcode": 2832,
                    "cases": 1
                },
                {
                    "postcode": 2125,
                    "cases": 1
                },
                {
                    "postcode": 2223,
                    "cases": 1
                },
                {
                    "postcode": 2103,
                    "cases": 1
                },
                {
                    "postcode": 2227,
                    "cases": 1
                },
                {
                    "postcode": 2104,
                    "cases": 1
                },
                {
                    "postcode": 2334,
                    "cases": 1
                },
                {
                    "postcode": 2111,
                    "cases": 1
                },
                {
                    "postcode": 2193,
                    "cases": 1
                },
                {
                    "postcode": 2040,
                    "cases": 1
                },
                {
                    "postcode": 2320,
                    "cases": 1
                },
                {
                    "postcode": 2146,
                    "cases": 1
                },
                {
                    "postcode": 2759,
                    "cases": 1
                },
                {
                    "postcode": 2133,
                    "cases": 1
                },
                {
                    "postcode": 2068,
                    "cases": 1
                },
                {
                    "postcode": 2500,
                    "cases": 1
                },
                {
                    "postcode": 2063,
                    "cases": 1
                },
                {
                    "postcode": 2039,
                    "cases": 1
                },
                {
                    "postcode": 2304,
                    "cases": 1
                },
                {
                    "postcode": 2046,
                    "cases": 1
                },
                {
                    "postcode": 2528,
                    "cases": 1
                },
                {
                    "postcode": 2850,
                    "cases": 1
                },
                {
                    "postcode": 2558,
                    "cases": 1
                },
                {
                    "postcode": 2745,
                    "cases": 1
                },
                {
                    "postcode": 2519,
                    "cases": 1
                },
                {
                    "postcode": 2171,
                    "cases": 1
                },
                {
                    "postcode": 2042,
                    "cases": 1
                },
                {
                    "postcode": 2323,
                    "cases": 1
                },
                {
                    "postcode": 2075,
                    "cases": 1
                },
                {
                    "postcode": 2502,
                    "cases": 1
                },
                {
                    "postcode": 2533,
                    "cases": 1
                },
                {
                    "postcode": 2117,
                    "cases": 1
                },
                {
                    "postcode": 2222,
                    "cases": 1
                },
                {
                    "postcode": 2752,
                    "cases": 1
                },
                {
                    "postcode": 2217,
                    "cases": 1
                },
                {
                    "postcode": 2218,
                    "cases": 1
                },
                {
                    "postcode": 2262,
                    "cases": 1
                },
                {
                    "postcode": 2880,
                    "cases": 1
                },
                {
                    "postcode": 2794,
                    "cases": 1
                }
            ]
        }
    ]
}
```