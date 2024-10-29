import logging
from typing import Optional, Iterable

from werkzeug.exceptions import BadRequest

from model.history.HistoryAction import HistoryAction

LOG = logging.getLogger(__name__)

__all__ = ['get_actions']


def get_actions(num_serie: Optional[str] = None, auteur: Optional[str] = None,
                limit: int = 10) -> Iterable[HistoryAction]:
    if num_serie is not None and len(num_serie) > 12:
        raise BadRequest('Mauvais numéro de série de rame')
    if auteur is not None and len(auteur) > 50:
        raise BadRequest('Operateur incorrect')
    if limit == 0 or limit < -1:
        raise BadRequest('Limite de résultats incorrecte')

    filters = dict()
    if num_serie is not None:
        filters['num_serie'] = num_serie
    if auteur is not None:
        filters['auteur'] = auteur

    actions = HistoryAction.objects(**filters).order_by('-timestamp')
    if limit != -1:
        actions = actions[:limit]
    return actions
