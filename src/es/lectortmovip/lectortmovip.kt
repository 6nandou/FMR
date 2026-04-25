package eu.kanade.tachiyomi.extension.es.lectortmovip

import eu.kanade.tachiyomi.network.GET
import eu.kanade.tachiyomi.source.model.FilterList
import eu.kanade.tachiyomi.source.model.Page
import eu.kanade.tachiyomi.source.model.SChapter
import eu.kanade.tachiyomi.source.model.SManga
import eu.kanade.tachiyomi.source.online.ParsedHttpSource
import okhttp3.Request
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element

class LectorTmovip : ParsedHttpSource() {

    override val name = "LectorTMO VIP"
    override val baseUrl = "https://lectortmo.vip"
    override val lang = "es"
    override val supportsLatest = true

    override fun popularMangaRequest(page: Int): Request = GET("$baseUrl/biblioteca?page=$page", headers)
    
    override fun popularMangaSelector() = "div.element"

    override fun popularMangaFromElement(element: Element): SManga = SManga.create().apply {
        setUrlWithoutDomain(element.select("a").attr("href"))
        title = element.select("h3").text()
        thumbnail_url = element.select("img").attr("src")
    }

    override fun popularMangaNextPageSelector() = "a[rel=next]"

    // --- DETALLES ---
    override fun mangaDetailsParse(document: Document): SManga = SManga.create().apply {
        description = document.select("div.manga-description").text()
        genre = document.select("span.badge").joinToString { it.text() }
        status = SManga.ONGOING
    }

    override fun chapterListSelector() = "li.list-group-item"

    override fun chapterFromElement(element: Element): SChapter = SChapter.create().apply {
        setUrlWithoutDomain(element.select("a").attr("href"))
        name = element.select("a").text()
    }

    override fun pageListParse(document: Document): List<Page> {
        return document.select("div.wp-manga-chapter-img img").mapIndexed { i, element ->
            Page(i, "", element.attr("abs:src"))
        }
    }

    override fun latestUpdatesRequest(page: Int) = popularMangaRequest(page)
    override fun latestUpdatesSelector() = popularMangaSelector()
    override fun latestUpdatesFromElement(element: Element) = popularMangaFromElement(element)
    override fun latestUpdatesNextPageSelector() = popularMangaNextPageSelector()
    override fun searchMangaRequest(page: Int, query: String, filters: FilterList) = GET("$baseUrl/search?q=$query&page=$page", headers)
    override fun searchMangaSelector() = popularMangaSelector()
    override fun searchMangaFromElement(element: Element) = popularMangaFromElement(element)
    override fun searchMangaNextPageSelector() = popularMangaNextPageSelector()
    override fun imageUrlParse(document: Document) = ""
}
