(ns portfolio.views.utils)

(defn page-case
  "Switch between components based upon a key.
   Useful for top-level navigation."

  [key & args]
  (let [options (first args)
        state (last args)]
    ((key options) state)))
