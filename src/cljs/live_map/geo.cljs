(ns live-map.geo
  (:require [reagent.core :as reagent]))

(defonce d3-geo (.-geo js/d3))
(defonce width 960)
(defonce height 500)

(defn projection [width height]
  (-> d3-geo
    (.albersUsa)
    (.translate (array (/ width 2) (/ height 2)))
    (.scale (array 1000))))

(defn path [projection]
  (-> d3-geo
    (.path)
    (.projection projection)))

(defn svg [width height]
  (-> (.select js/d3 "#map-container")
    (.append "svg")
    (.attr "width" width)
    (.attr "height" height)))

; svg.selectAll("path")
; 	.data(json.features)
; 	.enter()
; 	.append("path")
; 	.attr("d", path)
; 	.style("stroke", "#fff")
; 	.style("stroke-width", "1")
; 	.style("fill", function(d) {
;
; 	// Get data value
; 	var value = d.properties.visited;
;
; 	if (value) {
; 	//If value exists…
; 	return color(value);
; 	} else {
; 	//If value is undefined…
; 	return "rgb(213,222,217)";
; 	}
; });

(defn append-map [svg path]
  (doall (-> svg
    (.append "path")
    (.attr "d", path)
    (.style "stroke" "#fff")
    (.style "stroke-width" "1")
    (.style "fill" "#000"))))

(defn map-with-d3-instructions [width height]
  (doall
    (.log js/console "svg" (svg width height))
    (.log js/console "projection" (projection width height))
    (.log js/console "path" (path (projection width height)))
    (append-map
      (svg width height)
      (path (projection width height))

      )))

(defn albers-us []
  [:div {:id "map-container"}])
