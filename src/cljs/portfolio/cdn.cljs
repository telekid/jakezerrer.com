(ns portfolio.cdn
  (:require [goog.string :as gstring]
            [goog.string.format]))

(goog-define cdn-url "https://s3.amazonaws.com/jakezerrerassets/%s")

(defn resource-url
  "Returns a resource url for a given resource `name`.
   In development mode, return a URL for the asset in the S3 bucket.
   In production, return a URL referencing CDN."
  [name]
  (gstring/format cdn-url name))
